import React from 'react'
import AddPerson from './AddPerson';
import { PersonList } from './PersonList';

const PersonPage = () => {
  return (
    <div>
        <div>
            <PersonList />
        </div>
        <div>
            <AddPerson />
        </div>
        
    </div>
  )
}

export default PersonPage