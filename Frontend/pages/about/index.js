import React from 'react'
import styles from '@/styles/About.module.css'
import people from '../../public/people.jpg'
import logo from '../../public/logosas.png'
import organizers from '../../public/organizers.jpg'
import event from '../../public/event.jpg'
import Image from 'next/image'

const index = () => {
  return (
    <div className={styles.container}>
      <div className={styles.info}>
        <h3>ABOUT US</h3>
        <h1>SPOTASPOT</h1>
        <p>Get hyped for endless possibilities of fun and excitement with our amazing app that makes discovering and creating awesome events a breeze!</p>
      </div>
      <div className={styles.img}>
      <Image src={logo} alt="" layout='fill' className={styles.image}/>
      </div>


      <div className={styles.img}>
      <Image src={event} alt="" layout='fill' className={styles.image} />
      </div>


      <div className={styles.info}>
        <h2>Event Tracking</h2>
        <p>Our goal is to create a platform where our users can easily find interesting events which are highly inaccessible or really hard to find.</p>
      </div>

     


      <div className={styles.info}>
        <h2>Users</h2>
        <p>We offer our users a wide range of events, including quizzes, workshops, music festivals, stand-up shows, and various others, which they can easily browse and choose from.</p>
      </div>

      <div className={styles.img}>
      <Image src={people} alt="" layout='fill' className={styles.image} />
      </div>

      <div className={styles.img}>
      <Image src={organizers} alt="" layout='fill' className={styles.image} />
      </div>


      <div className={styles.info}>
        <h2>Organizers</h2>
        <p>As an organizer, you have the ability to create and manage your own events. The app also provides you with real-time feedback on attendee engagement, allowing you to make adjustments and improvements to your event as needed. </p>
      </div>
      
    </div>
  )
}

export default index