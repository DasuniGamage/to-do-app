import React from 'react';
import { Box, Button, Typography, Stack, Paper } from '@mui/material';
import AddTaskIcon from '@mui/icons-material/AddTask';

const TaskList = ({ tasks, onDone }) => {
  return (
    <Stack 
        spacing={2}
        sx={{
            height: '100%',  
        }}
    >
      {tasks.map((task, index) => (
        <Paper
          key={index}
          elevation={4}
          sx={{
            p: 2,
            bgcolor: '#ffffff',
            borderBottom: '8px solid #4caf50',
            borderRadius: 2,
            wordWrap: 'break-word',
            overflowWrap: 'break-word',
          }}
        >
          <Box
            display="flex"
            justifyContent="space-between"
            alignItems="center"
          >
            <Box sx={{ flex: 1, minWidth: 0, pr: 2 }}>
                <Stack spacing={0.5}>
                    <Typography fontWeight="bold" fontSize="1.2rem">{task.title}</Typography>
                    <Typography variant="body2" fontSize="1rem" whiteSpace="pre-line">
                        {task.description}
                    </Typography>
              </Stack>
            </Box>

            <Button
              variant="outlined"
              color="success"
              startIcon={<AddTaskIcon/>}
              onClick={() => onDone(index)}
              sx={{ whiteSpace: 'nowrap' }}
            >
              Done
            </Button>
          </Box>
        </Paper>
      ))}
    </Stack>
  );
};

export default TaskList;
