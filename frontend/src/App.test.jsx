import { render, screen, waitFor, fireEvent } from '@testing-library/react';
import App from './App';  
import * as api from './api/tasks';
import { vi } from 'vitest';

vi.mock('./api/tasks');

test('loads and displays tasks, adds a new task', async () => {
  const tasks = [
    { id: 1, title: 'Existing Task', description: 'Desc', completed: false },
  ];

  api.getLatestTasks.mockResolvedValue(tasks);
  api.createTask.mockResolvedValue({ id: 2, title: 'New Task', description: 'Desc 2', completed: false });
  api.completeTask.mockResolvedValue({ id: 1, completed: true });

  render(<App />); 

  // wait for tasks to load
  await waitFor(() => expect(screen.getByText('Existing Task')).toBeInTheDocument());

  // add new task
  fireEvent.change(screen.getByLabelText(/title/i), { target: { value: 'New Task' } });
  fireEvent.change(screen.getByLabelText(/description/i), { target: { value: 'Desc 2' } });
  fireEvent.click(screen.getByRole('button', { name: /add task/i }));

  // wait for list refresh (assume getLatestTasks called again)
  await waitFor(() => expect(api.getLatestTasks).toHaveBeenCalledTimes(2));
});
