import React, {useEffect, useState} from 'react';
import axios from 'axios';

const App= () => {
    const [userList, setUserList] = useState([
        ""
    ]);
    useEffect(() => {
        const fetchUser = async () => {
            try{
                const response = await axios.get("/api/user/list");
                setUserList(response.data);
            }catch(e){
                console.error(e);
            }
        }
        fetchUser();
    },[]);
    return <ul>
        {userList.map(user =>(
            <li>{user}</li>
        ))}
    </ul>;
}

export default App;
