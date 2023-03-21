import React from 'react'
import {Card,CardActions,CardMedia,CardContent,Typography,Button} from '@mui/material'
import styles from '@/styles/PopularEvent.module.css'
import { TiLocation } from "react-icons/ti";
import { RxCalendar } from "react-icons/rx";
import { margin } from '@mui/system';
const PopularEvent = ({name,description,image,time,location,price}) => {
  return (
    <div>
        <Card sx={{ maxWidth: 345,maxHeight:500,background:"#064663",color:"#fff",lineHeight:2 }}>
      <div className={styles.cardImage}>
      <CardMedia
        className={styles.image}
        sx={{ height: 200 }}
        image={image}
        title=""
      />
      <Typography gutterBottom variant="h5" component="div" className={styles.imageText}>
          {name}
        </Typography>
      </div>
      
      <CardContent >

      
<Typography variant="body2" color="text.secondary" sx={{color:"#ECB365",fontSize:"1.1rem"}}>
<RxCalendar/> {time}
  </Typography>

  <Typography variant="body2" color="text.secondary" sx={{color:"#ECB365",fontSize:"1.1rem"}}>
    <TiLocation/> {location}
  </Typography>

  
  <Typography variant="body2" sx={{marginTop:"1rem"}}>
     {description}
  </Typography>

</CardContent>
<CardActions sx={{display:"flex",justifyContent:"space-between"}}>
  <h6 className={styles.price}>{price}$</h6>
  <Button size="small">Learn More</Button>
</CardActions>
      
      
    </Card>
    </div>
  )
}

export default PopularEvent