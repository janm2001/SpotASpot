import React, { useMemo, useState, useEffect } from "react";
import { useRouter } from "next/router";

import PopularEventsData from "@/data/PopularEventsData";
import Image from "next/image";
import Link from "next/link";
import styles from "@/styles/EventDetails.module.css";
import { TiLocation } from "react-icons/ti";
import { RxCalendar } from "react-icons/rx";
import { AiFillEdit } from "react-icons/ai";
import { AiFillDelete } from "react-icons/ai";
import { Typography, Rating, Button, IconButton } from "@mui/material";
import { BASE_URL } from "@/utils/global";

const EventDetails = () => {
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

  const handleDelete = async (id) => {
    const token = localStorage.getItem("token");
    const response = await fetch(BASE_URL + "/api/v1/event/delete/" + id, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        Authorization: `Bearer ${token}`,
      },
    });

    if (!response.ok) {
      console.log("error");
      return;
    }

    router.push("/");
  };

  const dateTimeEu = new Date(data.dateTime);
  const options = {
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "numeric",
    minute: "numeric",
    hour12: true,
  };
  const formattedDateTime = dateTimeEu.toLocaleString("en-GB", options);

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
                style={{ color: "#fff", fontSize: "1.1rem", margin: "0.5rem 0" }}
              >
                <RxCalendar /> {formattedDateTime}
              </Typography>

              <Typography
                variant="body2"
                color="text.secondary"
                style={{ color: "#fff", fontSize: "1.1rem", margin: "0.5rem 0" }}
              >
                <TiLocation /> {data.location}
              </Typography>
              <div className={styles.mapContainer}>
  <iframe
    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d22257.192290019793!2d15.957429887136506!3d45.788241849274684!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4765d67f741fcbe3%3A0x67a106895e23cb5a!2sZagreba%C4%8Dki%20Velesajam%2C%20d.o.o.!5e0!3m2!1shr!2shr!4v1687017762413!5m2!1shr!2shr"
    width="800"
    height="600"
    style={{ border: "0", width: "100%", height: "100%" }}
    allowFullScreen=""
    loading="lazy"
    referrerPolicy="no-referrer-when-downgrade"
  ></iframe>
</div>

              <Rating
                name="rating"
                value={rating}
                onChange={(event, newValue) => {
                  setRating(newValue);
                }}
                size="large"
                style={{
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
            <div className={styles.opis}>
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
