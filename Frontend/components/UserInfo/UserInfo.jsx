import React from "react";

const UserInfo = ({ firstName, lastName, email, userName, role }) => {
  return (
    <div>
      <p>firstName: {firstName}</p>
      <p>lastName: {lastName}</p>
      <p>email: {email}</p>
      <p>userName: {userName}</p>
      <p>role: {role}</p>
    </div>
  );
};

export default UserInfo;
