import {
    Box,
    Collapse,
    List,
    ListItemButton,
    ListItemText,
} from '@mui/material';
import { useState } from 'react';
import { Link } from 'react-router-dom';

const Sidebar = () => {
    const [openOrder, setOpenOrder] = useState(false);
    const [openProduct, setOpenProduct] = useState(false);
    const [openStatistic, setOpenStatistic] = useState(false);
    return (
        <Box className="sidebar">
            <List component="nav" sx={{ width: '100%', padding: 0 }}>
                {/* order management */}
                <ListItemButton
                    sx={{
                        backgroundColor: '#eceff4',
                        mb: 2,
                    }}
                    onClick={() => setOpenOrder(!openOrder)}
                >
                    <ListItemText primary="Order Management" />
                </ListItemButton>
                <Collapse in={openOrder} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemText primary="Pending" />
                        </ListItemButton>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemText primary="Shipping" />
                        </ListItemButton>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemText primary="Direct delivery" />
                        </ListItemButton>
                    </List>
                </Collapse>

                {/* product management */}
                <ListItemButton
                    sx={{ backgroundColor: '#eceff4', mb: 2 }}
                    onClick={() => setOpenProduct(!openProduct)}
                >
                    <ListItemText primary="Product Management" />
                </ListItemButton>
                <Collapse in={openProduct} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        <ListItemButton
                            sx={{ pl: 4 }}
                            component={Link}
                            to="/warehouse-management/import"
                        >
                            <ListItemText primary="Import" />
                        </ListItemButton>
                        <ListItemButton
                            sx={{ pl: 4 }}
                            component={Link}
                            to="/warehouse-management/product"
                        >
                            <ListItemText primary="Product" />
                        </ListItemButton>
                    </List>
                </Collapse>

                {/* statistics */}
                <ListItemButton
                    sx={{ backgroundColor: '#eceff4', mb: 2 }}
                    onClick={() => setOpenStatistic(!openStatistic)}
                >
                    <ListItemText primary="Statistics" />
                </ListItemButton>
                <Collapse in={openStatistic} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemText primary="Product Revenue" />
                        </ListItemButton>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemText primary="Agent Revenue" />
                        </ListItemButton>
                        <ListItemButton
                            sx={{ pl: 4 }}
                            component={Link}
                            to="/stats/supplier-imported"
                        >
                            <ListItemText primary="Supplier Import Volume" />
                        </ListItemButton>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemText primary="Revenue by time" />
                        </ListItemButton>
                    </List>
                </Collapse>
            </List>
        </Box>
    );
};
export default Sidebar;
