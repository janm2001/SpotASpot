import React, { useState } from "react";
import styles from "@/styles/OrganizorEvents.module.css";
import Button from "../Button/Button";
import CreateEventsForm from "../CreateEventsForm/CreateEventsForm";

const OrganizorEvents = () => {
  const [buttonActive, setButtonActive] = useState(false);
  console.log(buttonActive);
  return (
    <div className={styles.organizorWrap}>
      <Button buttonActive={buttonActive} setButtonActive={setButtonActive} />

      {buttonActive && (
        <CreateEventsForm
          buttonActive={buttonActive}
          setButtonActive={setButtonActive}
        />
      )}
    </div>
  );
};

export default OrganizorEvents;
