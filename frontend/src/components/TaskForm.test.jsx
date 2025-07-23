import { render, screen, fireEvent } from '@testing-library/react'
import TaskForm from './TaskForm'
import { vi } from 'vitest'

test('calls onAddTask with form data when submitted', () => {
  const mockAddTask = vi.fn()
  render(<TaskForm onAddTask={mockAddTask} />)

  fireEvent.change(screen.getByLabelText(/title/i), { target: { value: 'New Task' } })
  fireEvent.change(screen.getByLabelText(/description/i), { target: { value: 'Task description' } })

  fireEvent.click(screen.getByRole('button', { name: /add task/i }))

  expect(mockAddTask).toHaveBeenCalledTimes(1)
  expect(mockAddTask).toHaveBeenCalledWith({
    title: 'New Task',
    description: 'Task description',
  })
})
