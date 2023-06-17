import React from "react";
import {
  Card,
  CardActions,
  CardMedia,
  CardContent,
  Typography,
  Button,
} from "@mui/material";
import styles from "@/styles/PopularEvent.module.css";
import { TiLocation } from "react-icons/ti";
import { RxCalendar } from "react-icons/rx";
import Link from "next/link";
const PopularEvent = ({
  name,
  description,
  image,
  dateTime,
  location,
  price,
  id,
  category,
}) => {
  const dateTimeEu = new Date(dateTime);
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
    <Link href={`/${id}`}>
      <Card sx={{ background: "#064663", color: "#fff", lineHeight: 2 }}>
        <div className={styles.cardImage}>
          <CardMedia
            className={styles.image}
            sx={{ height: 200 }}
            image={"https://source.unsplash.com/800x600/?" + name.split("")[0]}
            title=""
          />
          <Typography
            gutterBottom
            variant="h5"
            component="div"
            className={styles.imageText}
          >
            {name}
          </Typography>
        </div>

        <CardContent>
          <Typography
            variant="body2"
            color="text.secondary"
            sx={{ color: "#ECB365", fontSize: "1.1rem" }}
          >
            <RxCalendar /> {formattedDateTime}
          </Typography>

          <Typography
            variant="body2"
            color="text.secondary"
            sx={{ color: "#ECB365", fontSize: "1.1rem" }}
          >
            <TiLocation /> {location}
          </Typography>

          <Typography
            variant="body2"
            sx={{
              marginTop: "1rem",
              height: "40px",
              overflow: "hidden",
              textOverflow: "ellipsis",
              whiteSpace: "no-wrap",
            }}
          >
            {description}
          </Typography>
        </CardContent>
        <CardActions sx={{ display: "flex", justifyContent: "space-between" }}>
          <Button size="small">Learn More</Button>
        </CardActions>
      </Card>
    </Link>
  );
};

export default PopularEvent;
