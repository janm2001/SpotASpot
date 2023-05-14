import React, { useMemo, useState, useEffect } from "react";
import EventsData from "@/data/EventsData";
import EventsNavbar from "@/components/EventsNavbar/EventsNavbar";
import Events from "@/components/Events/Events";
import styles from "@/styles/EventPage.module.css";
import { BASE_URL } from "@/utils/global";

const EventPage = () => {
  const [genre, setGenre] = useState("");
  const [search, setSearch] = useState("");
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await fetch(BASE_URL + "/api/v1/event/all", {
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
          },
        });
        if (!response.ok) {
          throw new Error("Failed to fetch data");
        }
        const getData = await response.json();

        setData(getData);
      } catch (error) {
        console.error(error);
      }
    };
    fetchEvents();
  }, []);

  const events = useMemo(() => {
    if (genre === "") {
      return data.content;
    } else {
      return data.content.filter((event) =>
        event.category.toLowerCase().includes(genre.toLowerCase())
      );
    }
  }, [data, genre]);

  return (
    <div>
      <EventsNavbar setGenre={setGenre} search={search} setSearch={setSearch} />
      <div className={styles.eventItems}>
        {events &&
          events
            .filter((event) =>
              event.name.toLowerCase().includes(search.toLowerCase())
            )
            .map((event) => <Events key={event.id} {...{ ...event }} />)}
      </div>
    </div>
  );
};

export default EventPage;
