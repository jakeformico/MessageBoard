import React, {useState} from 'react'
import api from '../../api/axiosConfig';
import { PersonList } from './PersonList';

export const GetPerson = () => {
	const [byName, setByName] = useState('');
	const [byPersonId, setById] = useState(0);
	const [persons, setPersons] = useState([]);

	const onSubmit = e => {
		e.preventDefault();

		if (byPersonId > 0) {
			api.get(byPersonId)
				.then(function (response) {
					setPersons([response.data]);
					console.log(response.data);
				});
		} else if (byName.value !== '') {
			api.get("person?name=" + byName)
				.then(function (response) {
					setPersons(response.data);
					console.log(response.data);
				});
		}

		setByName('');
		setById(0);
	}

	return (
		<>
		<form onSubmit={onSubmit}>
			<div className="form-control">
			<label htmlFor="byPersonId">Id</label>
			<input type="number" value={byPersonId} onChange={(e) => setById(e.target.value)} placeholder="0" />
			</div>

			<div className="form-control">
			<label htmlFor="byName">Name</label>
			<input type="text" value={byName} onChange={(e) => setByName(e.target.value)} placeholder="" />
			</div>

			<div className={'submit'}>
        		<button className={'btn'} type="submit">Submit</button>
      		</div>
		</form>

		<PersonList persons = {persons} />
		</>
	)
}