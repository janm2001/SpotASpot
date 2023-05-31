import React from 'react'
import styles from '@/styles/WelcomePage.module.css'
import people from '../../public/people.jpg'
import logo from '../../public/logosas.png'
import organizers from '../../public/organizers.jpg'
import event from '../../public/event.jpg'
import Image from 'next/image'
import Head from 'next/head'



import {Button } from "@mui/material";



const index = () => {
  return (
    <>
    <Head>
        <title>SpotASpot - Welcome</title>
        <meta name="description" content="SpotASpot - Welcome" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
    <div className={styles.container}>
      <div className={styles.info}>
        <h1>Explore the finest events in your city today!</h1>
        <p>Discover the best events in your city with our app, featuring a wide range of categories from workshops to sports events. Sign up now and start exploring!</p>
      </div>

     <Button className={styles.getStartedButton}>Get Started</Button>
      

      
    </div></>
  )
}

export default index