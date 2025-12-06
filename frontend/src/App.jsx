import { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  // 1. State to store the data from the backend
  const [users, setUsers] = useState([]);

  // 2. This runs AUTOMATICALLY when the page loads
  useEffect(() => {
    fetchUsers();
  }, []);

  // 3. The function that actually calls Spring Boot
  const fetchUsers = async () => {
    try {
      // We are calling the endpoint you created earlier
      const response = await axios.get('http://localhost:8080/user');
      console.log("Data received:", response.data); // Check your browser console!
      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching users:", error);
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>User List (From Spring Boot)</h1>
      
      {/* 4. Map through the data and display it */}
      <div style={{ display: 'grid', gap: '10px' }}>
        {users.map((user) => (
          <div key={user.usersid} style={{ border: '1px solid #ccc', padding: '10px', borderRadius: '8px' }}>
            <h3>{user.firstName} {user.lastName}</h3>
            <p>Email: {user.email}</p>
            <p>Role: {user.isStaff ? "Tutor" : "Student"}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;