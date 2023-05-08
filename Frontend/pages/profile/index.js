import UserInfo from "@/components/UserInfo/UserInfo";
import React, { useEffect, useState } from "react";

const Profile = () => {
  const [user, setUser] = useState([]);
  const BASE_URL = "http://localhost:8080";
  console.log(user);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await fetch(BASE_URL + "/api/v1/user/get");
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
    <div>
      <h1>User Information: </h1>
      {user && <UserInfo key={user.id} {...user} />}
    </div>
  );
};

export default Profile;
