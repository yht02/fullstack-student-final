import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";


function CreateStudent() {

    let navigate = useNavigate();

    const [student, setStudent] = useState({
      name: "",
      address: "",
      major: "",
      cgpa: "",
    });
  
    const { name, address, major, cgpa } = student;
  
    const onInputChange = (e) => {
      setStudent({ ...student, [e.target.name]: e.target.value });
    };
  
    const onSubmit = async (e) => {
      e.preventDefault();
      await axios.post("http://localhost:8080/student/create", student);
      navigate("/");
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Register student</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                Name
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter student's name"
                                name="name"
                                value={name}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Address" className="form-label">
                                Address
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter student's address"
                                name="address"
                                value={address}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Major" className="form-label">
                                Major
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter student's major"
                                name="major"
                                value={major}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="cgpa" className="form-label">
                                CGPA
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter student's cgpa"
                                name="cgpa"
                                value={cgpa}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">
                            Submit
                        </button>
                        <Link className="btn btn-outline-danger mx-2" to="/">
                            Cancel
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default CreateStudent;