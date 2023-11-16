
import React from 'react';
import { Table, TableBody, TableCell, TableHead, TableRow, Checkbox, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { deleteTodo } from '../services/httpService';

export default function TodoList({ todos, setTodos, fetchTodos }) {
  const handleDelete = async (id) => {
    try {
      await deleteTodo(id);
      fetchTodos(); 
    } catch (error) {
      console.error('Failed to delete todo:', error);
    }
  };

  return (
    <Table>
      <TableHead>
        <TableRow>
          <TableCell>Status</TableCell>
          <TableCell>Name</TableCell>
          <TableCell>Description</TableCell>
          <TableCell>Actions</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {todos.map((todo) => (
          <TableRow key={todo.id}>
            <TableCell padding="checkbox">
              <Checkbox checked={todo.completed} color="primary" />
            </TableCell>
            <TableCell>{todo.name}</TableCell>
            <TableCell>{todo.description}</TableCell>
            <TableCell>
              <IconButton aria-label="edit">
                <EditIcon color="primary" />
              </IconButton>
              <IconButton aria-label="delete" onClick={() => handleDelete(todo.id)}>
                <DeleteIcon color="error" />
              </IconButton>
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
}
