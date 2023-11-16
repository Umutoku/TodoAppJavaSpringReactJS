import React, { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { Container, Snackbar, TextField, Button, Table, TableBody, TableCell, TableHead, TableRow, Checkbox, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import Pagination from '@mui/material/Pagination';
import httpService from './services/httpService';
import { MESSAGE } from './clientData';
import { Page, GeneratePage } from './models/Page';

const App = () => {
  const { register, handleSubmit, reset, setValue, formState: { errors } } = useForm();
  const [todos, setTodos] = useState([]);
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [totalPages, setTotalPages] = useState(0);
  const [snackbar, setSnackbar] = useState({ open: false, message: '' });
  const [isEditMode, setIsEditMode] = useState(false); // Ekleyin

  useEffect(() => {
    fetchTodos();
  }, []);

  useEffect(() => {
    fetchTodosWithPagination(page, pageSize);
  }, [page, pageSize]);

  const fetchTodos = async () => {
    const fetchedTodos = await httpService.getTodos();
    setTodos(fetchedTodos);
  };

  const fetchTodosWithPagination = async (page, size) => {
    const response = await httpService.getTodosWithPagination(page, size);
    setTodos(response._embedded.todos);
    setTotalPages(response.page.totalPages);
  };


  const onSubmit = async (data) => {
    if (isEditMode) {
      await httpService.updateTodo(data);
      setSnackbar({ open: true, message: MESSAGE.UPDATE });
    } else {
      await httpService.createTodo(data);
      setSnackbar({ open: true, message: MESSAGE.CREATED });
    }
    reset();
    setIsEditMode(false);
    fetchTodos();
  };

  const handleEditTodo = (todo) => {
    setIsEditMode(true);
    Object.keys(todo).forEach(key => {
      setValue(key, todo[key]);
    });
  };

  const handleDeleteTodo = async (id) => {
    await httpService.deleteTodo(id);
    fetchTodos();
    setSnackbar({ open: true, message: MESSAGE.DELETE });
  };

  const handlePatchTodoStatus = async (id, completedStatus) => {
    await httpService.patchTodoStatus(id, completedStatus);
    fetchTodos();
    setSnackbar({ open: true, message: MESSAGE.UPDATE });
  };

  const handleCloseSnackbar = () => {
    setSnackbar({ open: false, message: '' });
  };

  const handlePageChange = (event, value) => {
    setPage(value - 1);
  };

  return (
    <Container>
      <form onSubmit={handleSubmit(onSubmit)}>
        <TextField
          {...register('name', { required: true })}
          label="Name"
          variant="outlined"
          fullWidth
          error={!!errors.name}
          helperText={errors.name ? "Name is required" : ""}
        />
        <TextField
          {...register('description')}
          label="Description"
          variant="outlined"
          fullWidth
        />
        <Button
          type="submit"
          variant="contained"
          color="primary"
        >
          {isEditMode ? "Update" : "Create"}
        </Button>
      </form>
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
          {todos.map(todo => (
            <TableRow key={todo.id}>
              <TableCell>
                <Checkbox checked={todo.completed} onChange={() => handlePatchTodoStatus(todo.id, !todo.completed)} />
              </TableCell>
              <TableCell>{todo.name}</TableCell>
              <TableCell>{todo.description}</TableCell>
              <TableCell>
                <IconButton onClick={() => handleEditTodo(todo)}><EditIcon /></IconButton>
                <IconButton onClick={() => handleDeleteTodo(todo.id)}><DeleteIcon /></IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Pagination count={totalPages} page={page + 1} onChange={handlePageChange} />
      <Snackbar open={snackbar.open} autoHideDuration={6000} onClose={handleCloseSnackbar} message={snackbar.message} />
    </Container>
  );
};

export default App;
