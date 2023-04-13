import React, { useEffect, useState } from "react";
import EventsData from "@/data/EventsData";
import EventsNavbar from "@/components/EventsNavbar/EventsNavbar";
import Events from "@/components/Events/Events";

const EventPage = () => {
  const styles = {
    width:"90%",
    margin:'0 auto',
    display:'grid',
    gridTemplateColumns:'repeat(2,1fr)',
    gap:'10px'
}

const [genre,setGenre] = useState(null);
const [search,setSearch] = useState("");
const [event,setEvent] = useState({...EventsData});


useEffect(() =>{
  
},[genre]);

const filterBySearch = () => {
    const filteredData = EventsData.includes(search.toLowerCase());

    
 
};

  
  return (
    <div>
      <EventsNavbar setGenre={setGenre} search={search} setSearch={setSearch}/>
      <div style={styles}>
      {genre ? EventsData.events.filter((event) => event.name.toLowerCase().includes(search.toLowerCase())).map((event) => <Events key={event.id} {...{...event}} />) : EventsData.events.filter((event) => event.name.toLowerCase().includes(search.toLowerCase())).map((event) => <Events key={event.id} {...{...event}} />) }
      </div>
    </div>
  );
};

export default EventPage;
