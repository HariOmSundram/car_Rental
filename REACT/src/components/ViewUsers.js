import { useState } from "react"

export default function ViewUsers() {


   const [users, setUsers] = useState([])
   //use effect for pranav

    return (
        <div>
            <h1> Users List </h1>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Userid</th>
                        <th scope="col">UserName</th>
                        <th scope="col">Role</th>
                        <th scope="col">--</th>
                    </tr>
                </thead>
                <tbody class="table-group-divider">

                    {users.map((user) => {
                        return (
                            <tr>
                                <td>{user.userId}</td>
                                <td>{user.userName}</td>
                                <td>{user.role.roleName}</td>
                                <td>
                                    <button type="button" class="btn btn-secondary">Edit</button>
                                    <button type="button" class="btn btn-danger">Delete</button>
                                </td>
                            </tr>
                        )
                    })}

                </tbody>
            </table>
        </div>
    )
}