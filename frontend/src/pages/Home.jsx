import React from 'react'
import axios from 'axios'
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";



function Home() {
    const [students, setStudents] = useState([]);
    const { id } = useParams();

    useEffect(() => {
        loadStudents();
    }, []);

    const loadStudents = async () => {
        const result = await axios.get("http://localhost:8080/student/students");
        setStudents(result.data);
    };

    const deleteStudent = async (id) => {
        await axios.delete(`http://localhost:8080/student/delete/${id}`);
        loadStudents();
      };
    

    return (
        <div className="container">
            <div className="py-4">
                <table className="table table-striped border shadow">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">Major</th>
                            <th scope="col">CGPA</th>
                            <th scope="col">Action</th>

                        </tr>
                    </thead>
                    <tbody className="table-group-divider">
                        {students.map((student, index) => (
                            <tr>
                                <th scope="row" key={index}>
                                    {index + 1}
                                </th>
                                <td>{student.name}</td>
                                <td>{student.address}</td>
                                <td>{student.major}</td>
                                <td>{student.cgpa}</td>
                                <td>
                                    <Link
                                        className="btn btn-primary mx-2"
                                        to={`/viewstudent/${student.id}`}
                                    >
                                        View
                                    </Link>
                                    <Link
                                        className="btn btn-outline-primary mx-2"
                                        to={`/editstudent/${student.id}`}
                                    >
                                        Edit
                                    </Link>
                                    <button
                                        className="btn btn-danger mx-2"
                                        onClick={() => deleteStudent(student.id)}
                                    >
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Home