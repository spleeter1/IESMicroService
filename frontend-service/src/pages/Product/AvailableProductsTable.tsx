import {
    Table,
    TableHead,
    TableBody,
    TableRow,
    TableCell,
    TableContainer,
    Paper,
    Checkbox,
    Box,
} from '@mui/material';

export interface Product {
    id: number;
    name: string;
    importPrice: number;
    salePrice: number;
    supplierId: number;
    supplierName: string;
    quantity: number;
}

interface AvailableProductsTableProps {
    products: Product[];
    checkedIds: number[];
    onCheck: (productId: number) => void;
}

const AvailableProductsTable = ({
    products,
    checkedIds,
    onCheck,
}: AvailableProductsTableProps) => {
    return (
        <Box sx={{ height: '85%', paddingBottom: 1 }}>
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
                <Table size="small" stickyHeader>
                    <TableHead
                        sx={{
                            backgroundColor: '#f5f5f5',
                        }}
                    >
                        <TableRow>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }} />
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                ID
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Name
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Quantity
                            </TableCell>
                            <TableCell sx={{ backgroundColor: '#f5f6fa' }}>
                                Import Price
                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {products.map(product => (
                            <TableRow key={product.id}>
                                <TableCell>
                                    <Checkbox
                                        checked={checkedIds.includes(
                                            product.id
                                        )}
                                        onChange={() => onCheck(product.id)}
                                    />
                                </TableCell>
                                <TableCell>{product.id}</TableCell>
                                <TableCell>{product.name}</TableCell>
                                <TableCell>{product.quantity}</TableCell>
                                <TableCell>{product.importPrice}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default AvailableProductsTable;
