import React, { useState } from 'react'
import { Link, Outlet } from 'react-router-dom';

export default function Admin() {
    //const [users, setUsers] = useState([]);

    /* function handleDisplayUsers() {
        fetch('http://localhost:9000/getalluser')
            .then(res => res.json())
            .then(data => {
                //console.log(data)
                setUsers(data)
            })

    } */



    return (
        <div>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">CarRental</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <Link class="nav-link active" aria-current="page" to="users">Users</Link>
                            </li>
                            <li class="nav-item">
                                <Link  class="nav-link active" aria-current="page" to="logout" >Logout</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <h1> Welcome Admin </h1>
            <Outlet />
            
        </div>
    )
}
