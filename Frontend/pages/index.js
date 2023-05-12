import Head from "next/head";

import { Inter } from "next/font/google";
import styles from "@/styles/Home.module.css";
import Carousel from "react-material-ui-carousel";
import CarouselItem from "@/components/CarouselItem/CarouselItem";
import PopularEvent from "@/components/PopularEvent/PopularEvent";
import favicon from "../public/favicon/favicon-16x16.png";

import { Button, Paper } from "@mui/material";
import { useEffect, useState } from "react";

const inter = Inter({ subsets: ["latin"] });

export default function Home() {
  const [data, setData] = useState([]);
  const dummyCarouselEvents = [
    {
      id: 1,
      name: "Carnival of Rio de Janeiro",
      picture:
        "https://images.unsplash.com/photo-1522008629172-0c17aa47d1ee?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      location: "Rio de Janeiro, Brazil",
      date: "February 25 - March 5, 2023",
    },
    {
      id: 2,
      name: "Eurovision Song Contest",
      picture:
        "https://images.unsplash.com/photo-1670611835444-163e408ab4ae?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=987&q=80",
      location: "Lisbon, Portugal",
      date: "May 14-18, 2024",
    },
    {
      id: 3,
      name: "Burning Man",
      picture:
        "https://images.unsplash.com/photo-1609847772945-c6e77f92cf10?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80",
      location: "Black Rock Desert, Nevada, United States",
      date: "August 27 - September 4, 2023",
    },
    {
      id: 4,
      name: "Holi Festival",
      picture:
        "https://images.unsplash.com/photo-1603228254119-e6a4d095dc59?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aG9saSUyMGZlc3RpdmFsfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
      location: "India",
      date: "March 17, 2024",
    },
    {
      id: 5,
      name: "Comic-Con International",
      picture:
        "https://images.unsplash.com/photo-1605663864774-748f5f858a08?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      location: "San Diego, California, United States",
      date: "July 20-24, 2023",
    },
  ];

  const dummyEvents = [
    {
      id: 1,
      name: "Coding Workshop",
      description:
        "Learn the basics of coding with this beginner-friendly workshop.",
      image:
        "https://images.unsplash.com/photo-1659301254614-8d6a9d46f26a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      time: "2023-05-12 10:00:00",
      location: "The Tech Center",
      price: 50.0,
    },
    {
      id: 2,
      name: "Painting Workshop",
      description:
        "Unleash your creativity and learn to paint with this hands-on workshop.",
      image:
        "https://images.unsplash.com/photo-1659301254614-8d6a9d46f26a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      time: "2023-06-15 14:00:00",
      location: "The Art Studio",
      price: 75.0,
    },
    {
      id: 3,
      name: "Pub Quiz Night",
      description:
        "Put your knowledge to the test with our weekly pub quiz night.",
      image:
        "https://images.unsplash.com/photo-1528100315649-ebfe49f792a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      time: "2023-04-20 19:00:00",
      location: "The Local Pub",
      price: 10.0,
    },
    {
      id: 4,
      name: "Writing Workshop",
      description:
        "Hone your writing skills with this workshop led by a professional writer.",
      image:
        "https://images.unsplash.com/photo-1659301254614-8d6a9d46f26a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      time: "2023-07-05 10:00:00",
      location: "The Writers' Room",
      price: 100.0,
    },
    {
      id: 5,
      name: "Cooking Workshop",
      description:
        "Learn to cook like a pro with this hands-on cooking workshop.",
      image:
        "https://images.unsplash.com/photo-1659301254614-8d6a9d46f26a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      time: "2023-08-17 16:00:00",
      location: "The Culinary Institute",
      price: 90.0,
    },
    {
      id: 6,
      name: "Trivia Night",
      description:
        "Gather your friends and join us for a fun-filled night of trivia and games.",
      image:
        "https://images.unsplash.com/photo-1528100315649-ebfe49f792a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      time: "2023-05-25 19:00:00",
      location: "The Local Pub",
      price: 15.0,
    },
    {
      id: 7,
      name: "Photography Workshop",
      description:
        "Capture stunning photos with this beginner-friendly photography workshop.",
      image:
        "https://images.unsplash.com/photo-1659301254614-8d6a9d46f26a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      time: "2023-09-01 11:00:00",
      location: "The Photography Studio",
      price: 60.0,
    },
    {
      id: 8,
      name: "Film Trivia Night",
      description:
        "Show off your movie knowledge with our film trivia night, featuring questions about all your favorite films.",
      image:
        "https://images.unsplash.com/photo-1528100315649-ebfe49f792a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
      time: "2023-06-08 19:00:00",
      location: "The Local Pub",
      price: 10.0,
    },
  ];

  useEffect(() => {
    const fetchEvents = async () => {
      const response = await fetch("http://localhost:8080/api/v1/event/all", {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      });
      const getData = await response.json();
      setData(getData);
    };
    fetchEvents();
  }, []);
  console.log(data);
  return (
    <>
      <Head>
        <title>SpotASpot - Home</title>
        <meta name="description" content="SpotASpot - Home" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href={favicon} />
      </Head>
      <main>
        <Carousel>
          {dummyCarouselEvents.map((event) => (
            <CarouselItem key={event.id} {...{ ...event }} />
          ))}
        </Carousel>

        <h2 className={styles.popularEventsTitle}>Popular Events</h2>

        <div className={styles.popularEvents}>
          {data.content &&
            data.content.map((event) => (
              <PopularEvent key={event.id} {...{ ...event }} />
            ))}
        </div>
      </main>
    </>
  );
}
