import React, { useState, useEffect } from "react";
import UserEvents from "@/components/UserEvents/UserEvents";
import OrganizorEvents from "@/components/OrganizorEvents/OrganizorEvents";

const index = () => {
  const [role, setRole] = useState("ORGANIZER");
  const BASE_URL = "http://localhost:8080";
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
          setRole(data.role);
        }
      } catch (error) {
        console.error(error);
      }
    };

    fetchUserData();
  }, []);
  return (
    <div>
      {role === "USER" ? (
        <UserEvents role={role} />
      ) : (
        <OrganizorEvents role={role} />
      )}
    </div>
  );
};

export default index;
