import React, { useEffect, useState } from 'react';
import {
  Box,
  Grid,
  Divider,
  useMediaQuery,
  useTheme
} from '@mui/material';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import { completeTask, createTask, getLatestTasks } from './api/tasks';

const App = () => {
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('sm'));

  const [tasks, setTasks] = useState([]);

  const fetchTasks = async() =>{
    try {
      const latest =await getLatestTasks();
      setTasks(latest);
      
    } catch (error) {
      console.error("Error fetching Tasks",error);    
    }
  }
  useEffect(()=>{
    fetchTasks();
  },[]);

  const handleAddTask = async (task) => {
    try {
      await createTask(task);
      await fetchTasks();
    } catch (error) {
      console.error('Error adding task', error);
    }
  };

  const handleMarkDone = async (index) => {
    try {
      const taskId = tasks[index].id;
      await completeTask(taskId);
      await fetchTasks(); // refresh task list
    } catch (error) {
      console.error('Error completing task', error);
    }
  };

  return (
    <Box
      sx={{
        minHeight: '100vh',
        width: '100vw',
        display: 'flex',
        justifyContent: 'center',
        alignItems: isMobile ? 'flex-start' : 'center',
        px: 2,
        py: isMobile ? 4 : 0,
        boxSizing: 'border-box',
        backgroundImage: `url('/bg.jpg')`, 
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
      }}
    >
      <Box
        sx={{
              width: '90vw',
              height: '90vh',
              maxWidth: '100%',
              borderRadius: 4,
              background: 'rgba(2, 7, 56, 0.461)',
              boxShadow: '0 8px 32px 0 rgba(31, 38, 135, 0.37)',
              backdropFilter: 'blur(12px)',
              WebkitBackdropFilter: 'blur(20px)',
              border: '1px solid rgba(255, 255, 255, 0.18)',
              p: isMobile ? 2 : 4,
              boxSizing: 'border-box',
        }}
      >

        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            height: '100%'
          }}
        >
          <Box
            sx={{
              flex: 1,
              display: "flex",
              flexDirection: isMobile ? "column" : "row",
              overflow: "hidden",
            }}
          >
            <Box
              sx={{
                flex: 1,
                display: "flex",
                flexDirection: "column",
                overflow: "auto",
                p: 2,
              }}
            >
              <TaskForm onAddTask={handleAddTask} />
            </Box>

            <Divider
              orientation={isMobile ? "horizontal" : "vertical"}
              flexItem
              sx={{
                bgcolor: "rgba(250, 246, 246, 0.288)",
                width: isMobile ? '100%' : '3px',  
                height: isMobile ? '3px' : 'auto', 
                alignSelf: 'stretch',              
                borderRadius: 2,                   
              }}
            />
            <Box
              sx={{
                flex: 1,
                display: "flex",
                flexDirection: "column",
                overflow: "auto",
                p: 2,
                pb: 10,
              }}
            >
            <TaskList tasks={tasks} onDone={handleMarkDone} />
            </Box>
          </Box>
        </Box>
      </Box>
    </Box>
  );
};

export default App;
