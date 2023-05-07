import React, { useState } from "react";
import UserEvents from "@/components/UserEvents/UserEvents";
import OrganizorEvents from "@/components/OrganizorEvents/OrganizorEvents";

const index = () => {
  const [role, setRole] = useState("ORGANIZOR");
  return <div>{role === "USER" ? <UserEvents /> : <OrganizorEvents />}</div>;
};

export default index;
