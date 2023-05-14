import React, { useMemo, useState, useEffect } from "react";
import { useRouter } from "next/router";

import PopularEventsData from "@/data/PopularEventsData";
import Image from "next/image";
import Link from "next/link";
import styles from "@/styles/EventDetails.module.css";
import { TiLocation } from "react-icons/ti";
import { RxCalendar } from "react-icons/rx";
import { Typography, Rating, Button } from "@mui/material";
import { BASE_URL } from "@/utils/global";

const PopularEventDetails = () => {
  const router = useRouter();

  const { id } = router.query;
  console.log("Id", id);
  const [rating, setRating] = useState(null);
  const [data, setData] = useState([]);

  // const event = PopularEventsData.events.filter(
  //   (event) => event.id.toString() === id
  // );

  useEffect(() => {
    const fetchEvents = async () => {
      const response = await fetch(BASE_URL + "/api/v1/event/get/" + id, {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      });
      const getData = await response.json();
      setData(getData);
    };
    fetchEvents();
  }, [id]);

  console.log(data);

  return (
    <div>
      {data.id && (
        <div key={data.id} className={styles.eventDetails}>
          <div className={styles.wrap}>
            <div className={styles.img}>
              <Image
                src={
                  "https://source.unsplash.com/800x600/?" +
                  data.name.split("")[0]
                }
                alt=""
                layout="fill"
                className={styles.image}
              />
            </div>

            <div className={styles.eventInfo}>
              <h1 className={styles.header}>{data.name}</h1>
              <Typography
                variant="body2"
                color="text.secondary"
                sx={{ color: "#fff", fontSize: "1.1rem", margin: "0.5rem 0" }}
              >
                <RxCalendar /> {data.dateTime}
              </Typography>

              <Typography
                variant="body2"
                color="text.secondary"
                sx={{ color: "#fff", fontSize: "1.1rem", margin: "0.5rem 0" }}
              >
                <TiLocation /> {data.location}
              </Typography>

              <Rating
                name="rating"
                value={rating}
                onChange={(event, newValue) => {
                  setRating(newValue);
                }}
                size="large"
                sx={{
                  margin: "1rem 0",
                  "& .MuiRating-iconEmpty": {
                    color: "#fff",
                  },
                  "& .MuiRating-iconFilled": {
                    color: "#ECB365",
                  },
                  "& .MuiRating-iconHover": {
                    color: "#ECB365",
                  },
                }}
              />
              <br />

              <Button className={styles.button}>Going</Button>
            </div>
          </div>

          <div className={styles.desc}>
            <h3 className={styles.description}>Description</h3>
            <p>{data.description}</p>
          </div>
        </div>
      )}
      <Link href="/">Back to Home</Link>
    </div>
  );
};

export default PopularEventDetails;
