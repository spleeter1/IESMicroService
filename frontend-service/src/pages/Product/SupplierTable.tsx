import {
    Box,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from '@mui/material';
import axios from 'axios';
import { useEffect, useState } from 'react';
import api from '../../api/apiClient';

interface Supplier {
    id: number;
    name: string;
    phoneNumber: string;
    address: string;
    email: string;
}
const SupplierTable = ({
    onSelect,
}: {
    onSelect: (supplier: Supplier) => void;
}) => {
    const [suppliers, setSuppliers] = useState<Supplier[]>([]);
    const [selectedId, setSelectedId] = useState<number | null>(null);
    useEffect(() => {
        const fetchSuppliers = async () => {
            try {
                const res = await api.get('/users/api/supplier/');
                console.log(res.data);
                setSuppliers(res.data);
            } catch (err) {
                console.error('Failed to fetch suppliers', err);
            }
        };

        fetchSuppliers();
    }, []);

    const handelSelect = (supplier: Supplier) => {
        setSelectedId(supplier.id);
        onSelect(supplier);
    };
    return (
        <Box sx={{ height: '100%' }}>
            <TableContainer
                component={Paper}
                sx={{
                    maxHeight: '100%',
                    overflowY: 'scroll',
                    borderRadius: 2,
                    border: '1px solid #e7e9ef ',
                    scrollbarWidth: 'none',
                }}
            >
                <Table stickyHeader>
                    <TableHead>
                        <TableRow sx={{ backgroundColor: '#f5f6fa' }}>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Supplier ID
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Supplier Name
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Supplier Email
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Contact
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Address
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {suppliers.map(s => (
                            <TableRow
                                key={s.id}
                                onClick={() => handelSelect(s)}
                                selected={selectedId === s.id}
                                sx={{
                                    '&:hover': {
                                        backgroundColor: '#f5f6fa',
                                        cursor: 'pointer',
                                    },
                                    backgroundColor:
                                        selectedId === s.id ? '#e7e9ef' : '',
                                }}
                            >
                                <TableCell>{s.id}</TableCell>
                                <TableCell>{s.name}</TableCell>
                                <TableCell>{s.email}</TableCell>
                                <TableCell>{s.phoneNumber}</TableCell>
                                <TableCell>{s.address}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};
export default SupplierTable;
