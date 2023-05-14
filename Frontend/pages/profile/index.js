import UserInfo from "@/components/UserInfo/UserInfo";
import React, { useEffect, useState } from "react";
import { Grid, Avatar } from "@mui/material";
import { deepOrange, deepPurple } from "@mui/material/colors";

const Profile = () => {
  const [user, setUser] = useState([]);
  const BASE_URL = "http://localhost:8080";
  console.log(user);

  useEffect(() => {
    const token = localStorage.getItem("token");
    console.log(token);
    const fetchUserData = async () => {
      try {
        const response = await fetch(BASE_URL + "/api/v1/user/get", {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            Authorization: `Bearer ${token}`,
          },
        });
        if (!response.ok) {
          throw new Error(`An error has occured: ${response.status}`);
        }
        const data = await response.json();
        if (data && typeof data === "object" && Object.keys(data).length > 0) {
          setUser(data);
        }
      } catch (error) {
        console.error(error);
      }
    };

    fetchUserData();
  }, []);

  return (
    <Grid
      container
      sx={{
        height: "90vh",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "start",
        gap: "1rem",
        padding: "1rem 0",
        fontSize: "1.5rem",
      }}
    >
      <h1>User Information: </h1>

      {user && <UserInfo key={user.id} {...user} />}
    </Grid>
  );
};

export default Profile;