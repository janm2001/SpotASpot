import React from "react";
import { Grid, Avatar } from "@mui/material";
import { deepOrange, deepPurple } from "@mui/material/colors";

const UserInfo = ({ firstName, lastName, email, username, role }) => {
  return (
    <Grid
      xs={6}
      p={0}
      sx={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        flexDirection: "column",
        gap: "1.5rem",
      }}
    >
      <Avatar sx={{ bgcolor: deepOrange[500] }}></Avatar>
      <p>firstName: {firstName}</p>
      <p>lastName: {lastName}</p>
      <p>email: {email}</p>
      <p>userName: {username}</p>
      <p>role: {role}</p>
    </Grid>
  );
};

export default UserInfo;
