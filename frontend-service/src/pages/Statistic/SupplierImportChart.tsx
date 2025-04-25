// src/components/SupplierImportChart.tsx

import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    ResponsiveContainer,
    Legend,
} from 'recharts';
import { SupplierStat } from './SupplierImportChartPage';

interface Props {
    data: SupplierStat[];
}

const SupplierImportChart = ({ data }: Props) => {
    return (
        <ResponsiveContainer width="100%" height="50%">
            <BarChart
                data={data}
                margin={{ top: 20, right: 30, left: 20, bottom: 5 }}
            >
                <CartesianGrid strokeDasharray="5" />
                <XAxis dataKey="supplierName" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar
                    dataKey="quantity"
                    fill="#1976d2"
                    name="Total Quantity"
                    barSize={40}
                />
            </BarChart>
        </ResponsiveContainer>
    );
};

export default SupplierImportChart;
