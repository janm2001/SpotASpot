import React, { useMemo, useState, useEffect } from "react";
import EventsData from "@/data/EventsData";
import EventsNavbar from "@/components/EventsNavbar/EventsNavbar";
import Events from "@/components/Events/Events";
import styles from "@/styles/EventPage.module.css";

const EventPage = () => {
  const [genre, setGenre] = useState("");
  const [search, setSearch] = useState("");
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/v1/event/all", {
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
