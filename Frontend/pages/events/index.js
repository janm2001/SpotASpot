import React, {useMemo, useState } from "react";
import EventsData from "@/data/EventsData";
import EventsNavbar from "@/components/EventsNavbar/EventsNavbar";
import Events from "@/components/Events/Events";
import styles from '@/styles/EventPage.module.css'

const EventPage = () => {
  

const [genre,setGenre] = useState("");
const [search,setSearch] = useState("");



const events = useMemo(() =>{
  if(genre === ""){
    return EventsData.events;
  }else{
    return EventsData.events.filter((event) => event.category.toLowerCase().includes(genre.toLowerCase()));
  }

  
},[genre])




  
  return (
    <div>
      <EventsNavbar setGenre={setGenre} search={search} setSearch={setSearch}/>
      <div className={styles.eventItems}>
      {events.filter((event) => event.name.toLowerCase().includes(search.toLowerCase())).map((event) => <Events key={event.id} {...{...event}} />)}
      </div>
    </div>
  );
};

export default EventPage;
