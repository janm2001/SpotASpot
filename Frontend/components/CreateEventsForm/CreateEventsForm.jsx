import React from "react";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  Box,
  Typography,
  Container,
} from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material/styles";

const CreateEventsForm = (props) => {
  const darkTheme = createTheme({
    palette: {
      mode: "dark",
    },
  });

  const handleSubmit = async (event) => {
    event.preventDefault();
    const submitData = new FormData(event.currentTarget);
    const response = await fetch(BASE_URL + "/api/v1/event/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify({
        name: submitData.get("name"),
        description: submitData.get("description"),
        category: submitData.get("category"),
        city: submitData.get("city"),
        locationn: submitData.get("location"),
        dateTime: submitData.get("dateTime"),
        isAvailable: true,
      }),
    });
    const data = await response.json();

    localStorage.setItem("token", data.token);

    router.push("/");
  };
  return (
    <div>
      <ThemeProvider theme={darkTheme}>
        <Container component="main" maxWidth="xs" sx={{ height: "85vh" }}>
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Typography component="h1" variant="h5">
              CreateEvent
            </Typography>
            <Box
              component="form"
              onSubmit={handleSubmit}
              noValidate
              sx={{ mt: 1 }}
            >
              <TextField
                margin="normal"
                required
                fullWidth
                id="name"
                label="Name"
                name="name"
                autoComplete="name"
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="description"
                label="Description"
                type="description"
                id="description"
                autoComplete="current-description"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="category"
                label="Category"
                name="category"
                autoComplete="category"
                autoFocus
              />

              <TextField
                margin="normal"
                required
                fullWidth
                name="city"
                label="City"
                type="city"
                id="city"
                autoComplete="City"
              />
              <TextField
                margin="normal"
                required
                fullWidth
                name="location"
                label="Location"
                type="location"
                id="location"
                autoComplete="Location"
              />

              <TextField
                margin="normal"
                id="dateTime"
                required
                fullWidth
                label="Pick a Date"
                type="datetime-local"
                defaultValue="2023-05-24T10:30"
                InputLabelProps={{
                  shrink: true,
                }}
              />

              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Create
              </Button>

              <Button
                type="cancel"
                onClick={() => props.setButtonActive(!props.buttonActive)}
                fullWidth
                variant="contained"
                color="error"
                sx={{ mt: 1, mb: 1 }}
              >
                Cancel
              </Button>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </div>
  );
};

export default CreateEventsForm;
