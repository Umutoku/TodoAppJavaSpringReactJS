import React from 'react';
import { useForm } from 'react-hook-form';
import { TextField, Button, Card, CardContent, CardHeader } from '@mui/material';
import { createTodo } from '../services/httpService';

export const TodoForm = ({ onAddTodo }) => {
  const { register, handleSubmit, reset, formState: { errors } } = useForm();

  const onSubmit = async (data) => {
    try {
      const response = await createTodo(data);
      onAddTodo(response.data);
      reset();
    } catch (error) {
      console.error('Error creating todo:', error);
    }
  };

  return (
    <Card>
      <CardHeader title="Create Todo" />
      <CardContent>
        <form onSubmit={handleSubmit(onSubmit)}>
          <TextField
            {...register('name', { required: 'Todo Name is required' })}
            label="Todo Name"
            error={!!errors.name}
            helperText={errors.name?.message}
            fullWidth
            margin="normal"
          />
          <TextField
            {...register('description')}
            label="Todo Description"
            fullWidth
            margin="normal"
            multiline
          />
          <Button type="submit" variant="contained" color="primary">
            Submit
          </Button>
        </form>
      </CardContent>
    </Card>
  );
};
