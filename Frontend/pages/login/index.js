import React from "react";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  
  Link,
  Grid,
  Box,
  Typography,
  Container,
} from "@mui/material";
import { useRouter } from "next/router";

import { createTheme, ThemeProvider } from '@mui/material/styles';

const index = () => {

  const router = useRouter();
  const BASE_URL = 'http://localhost:8080'

  const handleSubmit = async (event) => {
    event.preventDefault();
    const submitData = new FormData(event.currentTarget);
    const response = await fetch(BASE_URL+"/api/v1/auth/authenticate", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
      body: JSON.stringify({
        
        username: submitData.get("username"),
        password: submitData.get("password"),
      }),
    });
    const data = await response.json();
    console.log(data);
    localStorage.setItem("token", data.token);

    router.push("/");
    
  };
  const darkTheme = createTheme({
    palette: {
      mode: "dark",
    },
  });
  return (
    <div>
      <ThemeProvider theme={darkTheme}>
      <Container component="main" maxWidth="xs" sx={{height:"75vh"}}>
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
            Login
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
              Sign In
            </Button>
            <Grid container>
              
              <Grid item>
                <Link href="/signup" variant="body2">
                  {"Don't have an account? Sign Up"}
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