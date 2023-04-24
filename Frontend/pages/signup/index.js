import React from "react";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  FormControlLabel,
  Checkbox,
  Link,
  Grid,
  Box,
  Typography,
  Container,
  FormGroup,
  Select,
  MenuItem,
} from "@mui/material";
import { useRouter } from "next/router";

import { createTheme, ThemeProvider } from "@mui/material/styles";

const index = () => {
  const BASE_URL = 'http://localhost:8080'
  const router = useRouter();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const submitData = new FormData(event.currentTarget);
    const response = await fetch(BASE_URL+"/api/v1/auth/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify({
        firstName: submitData.get("firstName"),
        lastName: submitData.get("lastName"),
        role: submitData.get("role"),
        email: submitData.get("email"),
        username: submitData.get("username"),
        password: submitData.get("password"),
      }),
    });
    const data = await response.json();
    
    localStorage.setItem("token", data.token);

    router.push("/");

    // console.log({
    //   firstName: data.get("firstName"),
    //   lastName: data.get("lastName"),
    //   role: data.get("role"),
    //   email: data.get("email"),
    //   username: data.get("username"),
    //   password: data.get("password"),
    // });
  };
  const darkTheme = createTheme({
    palette: {
      mode: "dark",
    },
  });
  return (
    <div>
      <ThemeProvider theme={darkTheme}>
        <Container component="main" maxWidth="xs" sx={{ height: "100vh" }}>
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}></Avatar>
            <Typography component="h1" variant="h5">
              Sign up
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
                id="firstName"
                label="firstName"
                name="firstName"
                autoComplete="firstName"
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="lastName"
                label="lastName"
                name="lastName"
                autoComplete="lastName"
                autoFocus
              />
              <Select
                labelId="Select Role"
                id="role"
                name="role"
                label="Role"
                defaultValue={"USER"}
              >
                <MenuItem value={"USER"}>User</MenuItem>
                <MenuItem value={"ORGANIZOR"}>Organizor</MenuItem>
              </Select>
              <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                autoFocus
              />
              <TextField
                margin="normal"
                required
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoComplete="username"
                autoFocus
              />

              <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
              />

              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Sign Up
              </Button>
              <Grid container>
                <Grid item xs>
                  <Link href="/login" variant="body2">
                    Already have an account? Login
                  </Link>
                </Grid>
              </Grid>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </div>
  );
};

export default index;