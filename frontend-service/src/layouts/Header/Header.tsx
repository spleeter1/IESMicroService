const Header = () => {
    return (
        <div
            style={{
                ...styles.general,
                // maxWidth: '100%',
                display: 'flex',
                alignItems: 'center',
            }}
        >
            <h1 style={styles.title}>Import Export System</h1>
        </div>
    );
};

const styles = {
    general: {
        backgroundColor: '#ffffff',
        padding: '12px',
        border: '1px solidrgb(71, 71, 71)',
        boxShadow: '0 2px 4px rgba(95, 95, 95, 0.5)',
        // marginBottom: '5px',
    },
    title: {
        color: '#66b2ff',
        fontSize: '24px',
        margin: 0,
    },
};

export default Header;
