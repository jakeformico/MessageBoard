import React from 'react'
import AddApplication from './AddApplication';
import { ApplicationList } from './ApplicationList';

const ApplicationPage = () => {
  return (
    <div>
        <div>
            <ApplicationList />
        </div>
        <div>
            <AddApplication />
        </div>
        
    </div>
  )
}

export default ApplicationPage