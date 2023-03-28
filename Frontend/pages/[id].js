import React, { useState } from "react";
import { useRouter } from "next/router";

import PopularEventsData from "@/data/PopularEventsData";
import Image from "next/image";
import Link from "next/link";
import styles from "@/styles/EventDetails.module.css";
import { TiLocation } from "react-icons/ti";
import { RxCalendar } from "react-icons/rx";
import { Typography, Rating,Button } from "@mui/material";

const PopularEventDetails = () => {
  const router = useRouter();
  
  const { id } = router.query;
  const [rating,setRating] = useState(null);

  const event = PopularEventsData.events.filter((event) => event.id.toString() === id);

  

  return (
    <div>
      {event.map((e) => (
        <div key={e.id} className={styles.eventDetails}>
          <div className={styles.wrap}>
            <div className={styles.image}>
              <Image
                src={e.image}
                alt=""
                width={500}
                height={400}
                quality={100}
              />
            </div>

            <div className={styles.eventInfo}>
              <h1 className={styles.header}>{e.name}</h1>
              <Typography
                variant="body2"
                color="text.secondary"
                sx={{ color: "#fff", fontSize: "1.1rem",margin:"0.5rem 0" }}
              >
                <RxCalendar /> {e.time}
              </Typography>

              <Typography
                variant="body2"
                color="text.secondary"
                sx={{ color: "#fff", fontSize: "1.1rem",margin:"0.5rem 0" }}
              >
                <TiLocation /> {e.location}
              </Typography>

              <Rating
                name="rating"
                value={rating}
                onChange={(event, newValue) => {
                  setRating(newValue);
                }}
                size="large"
                sx={{
                  margin:"1rem 0",
                  "& .MuiRating-iconEmpty": {
                    color: "#fff"
                  },
                  "& .MuiRating-iconFilled": {
                    color: "#ECB365"
                  },
                  "& .MuiRating-iconHover": {
                    color: "#ECB365"
                  }
                }}
              />
              <br />

              <Button className={styles.button}>Going</Button>
            </div>
          </div>

          <h3 className={styles.description}>Description</h3>
          <p>{e.description}</p>
        </div>
      ))}
      <Link href="/">Back to Home</Link>
    </div>
  );
};

export default PopularEventDetails;
