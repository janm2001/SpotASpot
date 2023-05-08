import React from "react";
import { TiPlus } from "react-icons/ti";
import styles from "./Button.module.css";

const Button = ({ buttonActive, setButtonActive }) => {
  return (
    <button
      className={styles.button}
      onClick={() => setButtonActive(!buttonActive)}
    >
      Create Events
      <TiPlus />
    </button>
  );
};

export default Button;
