import { render, screen, fireEvent } from '@testing-library/react';
import TaskList from './TaskList';
import { vi } from 'vitest';

const tasksMock = [
  { id: 1, title: 'Task 1', description: 'Desc 1' },
  { id: 2, title: 'Task 2', description: 'Desc 2' },
];

test('renders task titles and descriptions', () => {
  render(<TaskList tasks={tasksMock} onDone={() => {}} />);

  expect(screen.getByText('Task 1')).toBeInTheDocument();
  expect(screen.getByText('Desc 2')).toBeInTheDocument();
});

test('calls onDone when Done button clicked', () => {
  const mockOnDone = vi.fn();

  render(<TaskList tasks={tasksMock} onDone={mockOnDone} />);

  fireEvent.click(screen.getAllByRole('button', { name: /done/i })[0]);

  expect(mockOnDone).toHaveBeenCalledWith(0);
});
