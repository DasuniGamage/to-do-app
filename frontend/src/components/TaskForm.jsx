import React, { useState } from "react";
import { TextField, Button, Typography, Stack, Box } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";

const TaskForm = ({ onAddTask }) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = () => {
    if (title.trim()) {
      onAddTask({ title, description });
      setTitle("");
      setDescription("");
    }
  };

  const textFieldStyles = {
    "& .MuiOutlinedInput-root": {
      color: "white",
      "& fieldset": {
        borderColor: "white",
      },
      "&:hover fieldset": {
        borderColor: "white",
      },
      "&.Mui-focused fieldset": {
        borderColor: "white",
      },
    },
  };

  return (
    <Stack spacing={2} alignItems="center">
      <Typography variant="h4" fontWeight="bold" color="white">
        Add a Task
      </Typography>

      <TextField
        label="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        variant="outlined"
        fullWidth
        InputProps={{
          style: { color: "white" },
        }}
        slotProps={{
          inputLabel: {
            style: { color: "white" },
          },
        }}
        sx={textFieldStyles}
      />

      <TextField
        label="Description"
        multiline
        minRows={3}
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        variant="outlined"
        fullWidth
        slotProps={{
          inputLabel: {
            style: { color: "white" },
          },
        }}
        sx={textFieldStyles}
      />

      <Box sx={{ width: "100%", display: "flex", justifyContent: "flex-end" }}>
        <Button
          variant="contained"
          startIcon={<AddIcon />}
          onClick={handleSubmit}
          sx={{
            bgcolor: "white",
            color: "black",
            py: 2,
            // minWidth: 300,
            width: '50%',
            "&:hover": {
              bgcolor: "#4caf50",
              color: "#fbf9f9",
            },
          }}
        >
          Add Task
        </Button>
      </Box>
    </Stack>
  );
};

export default TaskForm;
