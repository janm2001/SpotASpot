import React, { useMemo, useState, useEffect } from "react";
import { useRouter } from "next/router";

import Image from "next/image";
import Link from "next/link";
import styles from "@/styles/EventDetails.module.css";
import { TiLocation } from "react-icons/ti";
import { RxCalendar } from "react-icons/rx";
import { AiFillEdit } from "react-icons/ai";
import { AiFillDelete } from "react-icons/ai";
import { Typography, Rating, Button, IconButton } from "@mui/material";

const EventDetails = () => {
  const router = useRouter();

  const { id } = router.query;

  const [rating, setRating] = useState(null);
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchEvents = async () => {
      const response = await fetch(
        "http://localhost:8080/api/v1/event/get/" + id,
        {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
          },
        }
      );
      const getData = await response.json();
      setData(getData);
    };
    fetchEvents();
  }, [id]);

  const handleDelete = async (id) => {
    const token = localStorage.getItem("token");
    const response = await fetch(
      "http://localhost:8080/api/v1/event/delete/" + id,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (!response.ok) {
      console.log("error");
      return;
    }

    router.push("/");
  };

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
            <div>
              <h3 className={styles.description}>Description</h3>
              <p>{data.description}</p>
            </div>
            <div className={styles.buttons}>
              <IconButton>
                <AiFillEdit />
              </IconButton>

              <IconButton onClick={() => handleDelete(id)}>
                <AiFillDelete />
              </IconButton>
            </div>
          </div>
        </div>
      )}
      <Link href="/">Back to Home</Link>
    </div>
  );
};

export default EventDetails;
