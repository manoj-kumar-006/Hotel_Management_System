function Sidebar() {
    const locations = ["Mumbai","Delhi","Hyderabad","Bangalore","Chennai","Goa"];

    return (
        <div style={styles.sidebar}>
            <h4>Locations</h4>
            {locations.map((loc, i) => <p key={i}>{loc}</p>)}
        </div>
    );
}

const styles = {
    sidebar: {
        width: "200px",
        background: "#fff",
        padding: "15px",
        borderRadius: "10px",
        boxShadow: "0 2px 10px rgba(0,0,0,0.1)"
    }
};

export default Sidebar;