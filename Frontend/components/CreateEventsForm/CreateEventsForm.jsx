import React from "react";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  Box,
  Typography,
  Container,
  FormControl,
  InputLabel,
  Select,
  MenuItem
} from "@mui/material";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useRouter } from "next/router";
import { BASE_URL } from "@/utils/global";

const CreateEventsForm = (props) => {
  const darkTheme = createTheme({
    palette: {
      mode: "dark",
    },
  });

  const [category, setCategory] = React.useState('');

  const handleChange = (event) => {
    setCategory(event.target.value);
  };

  const router = useRouter();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const submitData = new FormData(event.currentTarget);
    const token = localStorage.getItem("token");
    const eventPost =async () => {
      const response = await fetch(BASE_URL + "/api/v1/event/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          name: submitData.get("name"),
          description: submitData.get("description"),
          category: category,
          city: submitData.get("city"),
          location: submitData.get("location"),
          dateTime: submitData.get("dateTime"),
          isAvailable: true,
        }),
      });
      // console.log(response.status);
      // console.log(await response.text());
      if (!response.ok) {
        console.log("error");
        return;
      }
    }

    const imageEventPost =async () => {
      const response = await fetch(BASE_URL + "/api/v1/event/"+id+"/image", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          image:submitData.get("image")
        }),
      });
      // console.log(response.status);
      // console.log(await response.text());
      if (!response.ok) {
        console.log("error");
        return;
      }
    }
    eventPost();
    /* imageEventPost(); */
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
              

                <FormControl fullWidth margin="normal">
                <InputLabel id="category">Category</InputLabel>
                <Select
                  labelId="category"
                  id="category"
                  value={category}
                  label="Category"
                  onChange={handleChange}
                >
                  <MenuItem value={'RADIONICA'}>Radionica</MenuItem>
                  <MenuItem value={'KVIZ'}>Kviz</MenuItem>
                  <MenuItem value={'KOMEDIJA'}>Komedija</MenuItem>
                  <MenuItem value={'KONCERT'}>Koncert</MenuItem>
                  <MenuItem value={'SPORT'}>Sport</MenuItem>
                  <MenuItem value={'DOBROTVORNI'}>Dobrotvorni</MenuItem>
                  <MenuItem value={'IGRA'}>Igra</MenuItem>
                  <MenuItem value={'NATJECANJE'}>Natjecanje</MenuItem>
                </Select>
              </FormControl>

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
                name="dateTime"
                required
                fullWidth
                label="Pick a Date"
                type="dateTime"
                defaultValue="2023-05-24T10:30"
                InputLabelProps={{
                  shrink: true,
                }}
              />
              <Button
                variant="contained"
                component="label"
              >
                Upload Image
                <input
                  type="file"
                  id="image"
                  name="image"
                  
                  hidden
                />
              </Button>



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
