import { Box } from '@mui/material';
import Header from './Header/Header';
import Sidebar from './Sidebar/Sidebar';
import { Outlet } from 'react-router-dom';

const MainLayout = () => {
    return (
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                backgroundColor: '#fcfcfc',
                height: '100vh',
            }}
        >
            <header>
                <Header />
            </header>

            <Box
                component="section"
                sx={{
                    flexGrow: 1,
                    display: 'flex',
                    flexDirection: 'row',
                }}
            >
                <Box
                    component="aside"
                    sx={{
                        width: '15%',
                        backgroundColor: '#f5f6fa',
                    }}
                >
                    <Sidebar />
                </Box>
                <Box
                    component="main"
                    sx={{ flexGrow: 1, padding: 2, backgroundColor: '#fcfcfc' }}
                >
                    <Outlet />
                </Box>
            </Box>
        </Box>
    );
};
export default MainLayout;
