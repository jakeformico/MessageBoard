import React, {useState} from 'react'
import api from '../api/axiosConfig';
import { CourseList } from './CourseList';

export const GetCourse = () => {
	const [byName, setByName] = useState('');
	const [byCourseId, setById] = useState(0);
	const [courses, setCourses] = useState([]);

	const onSubmit = e => {
		e.preventDefault();

		if (byCourseId > 0) {
			api.get("/courses" + byCourseId)
				.then(function (response) {
					setCourses([response.data]);
					console.log(response.data);
				});
		} else if (byName.value !== '') {
			api.get("/courses/course?name=" + byName)
				.then(function (response) {
					setCourses(response.data);
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
			<label htmlFor="byCourseId">Id</label>
			<input type="number" value={byCourseId} onChange={(e) => setById(e.target.value)} placeholder="0" />
			</div>

			<div className="form-control">
			<label htmlFor="byName">Name</label>
			<input type="text" value={byName} onChange={(e) => setByName(e.target.value)} placeholder="" />
			</div>

			<div className={'submit'}>
        		<button className={'btn'} type="submit">Submit</button>
      		</div>
		</form>

		<CourseList courses = {courses} />
		</>
	)
}