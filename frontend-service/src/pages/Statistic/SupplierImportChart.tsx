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
        <ResponsiveContainer width="100%" height={400}>
            <BarChart
                data={data}
                layout="vertical"
                margin={{ top: 20, right: 30, left: 80, bottom: 5 }}
            >
                <CartesianGrid strokeDasharray="5" />
                <XAxis type="number" />
                <YAxis dataKey="supplierName" type="category" />
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
