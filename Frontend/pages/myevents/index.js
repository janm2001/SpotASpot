import React, { useState } from 'react'
import UserEvents from '@/components/UserEvents/UserEvents';
import OrganizorEvents from '@/components/OrganizorEvents/OrganizorEvents';

const index = () => {
  const [role,setRole] = useState('USER');
  return (
    <div>
      {role==='USER' ? <UserEvents/> : <OrganizarEvents/>}
    </div>
  )
}

export default index