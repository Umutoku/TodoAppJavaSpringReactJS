import axios from 'axios';

const TODO_API = '/api/todos';

const httpService = {
  createTodo: (todo) => axios.post(TODO_API, todo),
  getTodos: () => axios.get(TODO_API).then(response => response.data._embedded.todos),
  deleteTodo: (id) => axios.delete(`${TODO_API}/${id}`),
  updateTodo: (todo) => axios.put(`${TODO_API}/${todo.id}`, todo),
  patchTodoStatus: (id, completedStatus) => axios.patch(`${TODO_API}/${id}`, { completed: completedStatus }),
  getTodosWithPagination: (page, size) => {
    const url = `${TODO_API}?page=${page}&size=${size}`;
    return axios.get(url).then(response => response.data);
  },
};

export default httpService;
