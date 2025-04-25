import { Box, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import axios from 'axios';
import SupplierImportChart from './SupplierImportChart';

export interface SupplierStat {
    id: number;
    quantity: number;
    supplierId: number;
    supplierName: string;
}

const SupplierImportChartPage = () => {
    const [stats, setStats] = useState<SupplierStat[]>([]);

    useEffect(() => {
        const fetchStats = async () => {
            try {
                const res = await axios.get(
                    `/statistics/api/stats/supplier-import`
                );
                setStats(res.data);
                console.log(res.data);
            } catch (err) {
                console.error('Failed to fetch supplier stats', err);
            }
        };
        fetchStats();
    }, []);

    return (
        <Box sx={{ padding: 4 }}>
            <Typography variant="h4" sx={{ marginBottom: 3 }}>
                Thống kê lượng hàng nhập theo nhà cung cấp
            </Typography>
            <SupplierImportChart data={stats} />
        </Box>
    );
};

export default SupplierImportChartPage;
