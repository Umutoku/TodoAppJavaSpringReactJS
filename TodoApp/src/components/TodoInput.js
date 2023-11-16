import React, { useState } from 'react';
import { Card, CardHeader, CardContent, TextField, Button } from '@mui/material';
import { createTodo } from '../services/httpService';

export default function TodoInput({ addTodo }) {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (name && description) {
      const newTodo = await createTodo({ name, description, completed: false });
      addTodo(newTodo.data);
      setName('');
      setDescription('');

    }
  };

  return (
    <Card>
      <CardHeader title="Create Todo" />
      <CardContent>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Todo Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            fullWidth
            required
          />
          <TextField
            label="Todo Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            fullWidth
            multiline
            required
          />
          <Button type="submit" variant="contained" color="primary">
            Add new task
          </Button>
        </form>
      </CardContent>
    </Card>
  );
}
